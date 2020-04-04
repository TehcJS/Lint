package me.hydos.lint.structurefeatures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.function.Function;

public class TaterVillageFeature extends AbstractTempleFeature<DefaultFeatureConfig> {

    public TaterVillageFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Override
    protected int getSeedModifier() {
        return 0;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return TaterVillageStructureStart::new;
    }

    @Override
    public String getName() {
        return "Tater Village";
    }

    @Override
    public int getRadius() {
        return 4;
    }
}