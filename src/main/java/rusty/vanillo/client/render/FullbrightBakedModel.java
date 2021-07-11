package rusty.vanillo.client.render;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FullbrightBakedModel implements IBakedModel {
    private static final LoadingCache<CacheKey, List<BakedQuad>> QUAD_CACHE = CacheBuilder.newBuilder().build(new CacheLoader<CacheKey, List<BakedQuad>>() {
        @Override
        public List<BakedQuad> load(CacheKey key) {
            return transformQuads(key.base.getQuads(key.state, key.side, key.random, EmptyModelData.INSTANCE), key.textures);
        }
    });

    private final IBakedModel base;
    private final Set<ResourceLocation> fullbrightSprites;

    public FullbrightBakedModel(IBakedModel base, Set<ResourceLocation> fullbrightSprites) {
        this.base = base;
        this.fullbrightSprites = fullbrightSprites;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return state == null ? base.getQuads(null, side, rand) : QUAD_CACHE.getUnchecked(new CacheKey(base, fullbrightSprites, rand, state, side));
    }

    @Override
    public boolean useAmbientOcclusion() {
        return base.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return base.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return base.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return base.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return base.getParticleIcon();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return base.getOverrides();
    }

    private static List<BakedQuad> transformQuads(List<BakedQuad> oldQuads, Set<ResourceLocation> textures) {
        List<BakedQuad> quads = new ArrayList<>(oldQuads);

        for (int i = 0; i < quads.size(); ++i) {
            BakedQuad quad = quads.get(i);

            if (textures.contains(quad.getSprite().getName())) {
                quads.set(i, transformQuad(quad));
            }
        }

        return quads;
    }

    private static BakedQuad transformQuad(BakedQuad quad) {
        int[] vertexData = quad.getVertices().clone();
        int step = vertexData.length / 4;

        // Set lighting to fullbright on all vertices
        vertexData[6] = 0x00F000F0;
        vertexData[6 + step] = 0x00F000F0;
        vertexData[6 + 2 * step] = 0x00F000F0;
        vertexData[6 + 3 * step] = 0x00F000F0;

        return new BakedQuad(vertexData, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
    }

    private static class CacheKey {
        private final IBakedModel base;
        private final Set<ResourceLocation> textures;
        private final Random random;
        private final BlockState state;
        private final Direction side;

        private CacheKey(IBakedModel base, Set<ResourceLocation> textures, Random random, BlockState state, Direction side) {
            this.base = base;
            this.textures = textures;
            this.random = random;
            this.state = state;
            this.side = side;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            CacheKey cacheKey = (CacheKey) obj;

            if (cacheKey.side != side) {
                return false;
            }

            return state.equals(obj);
        }

        @Override
        public int hashCode() {
            return state.hashCode();
        }
    }
}
