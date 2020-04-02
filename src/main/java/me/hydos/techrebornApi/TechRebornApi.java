package me.hydos.techrebornApi;

import me.hydos.techrebornApi.blocks.TRMachineBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.crafting.RebornRecipeType;
import reborncore.common.crafting.RecipeManager;
import techreborn.client.GuiType;

import java.util.HashMap;
import java.util.function.Supplier;

public class TechRebornApi {

    private static final HashMap<String, TRMachineBlock> blocks = new HashMap<>();
    private static final HashMap<String, BlockEntityType> blockEntities = new HashMap<>();
    private static final HashMap<String, RebornRecipeType> blockRecipes = new HashMap<>();
    public static void registerBlock(String modid, String identifier, Class<? extends RebornRecipe> recipe, ItemGroup itemGroup, Supplier<BlockEntity> blockEntityClass, GuiType<?> adventureTransformerGui){
        //Process all the information passed in
        TRMachineBlock processedBlock = new TRMachineBlock(true, adventureTransformerGui, blockEntityClass);
        RebornRecipeType processedRecipe = RecipeManager.newRecipeType(recipe, new Identifier(modid + ":" + identifier));
        BlockEntityType processedBlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, modid + ":" + identifier, BlockEntityType.Builder.create(blockEntityClass, processedBlock).build(null));

        //Register the item so it can be used
        Registry.register(Registry.BLOCK, new Identifier(modid, identifier), processedBlock);
        Registry.register(Registry.ITEM, new Identifier(modid, identifier), new BlockItem(processedBlock, new Item.Settings().group(itemGroup)));

        //Add it to the lists so it can be grabbed
        blocks.put(identifier, processedBlock);
        blockEntities.put(identifier, processedBlockEntity);
        blockRecipes.put(identifier, processedRecipe);
    }

    public static TRMachineBlock getBlock(String id){
        return blocks.get(id);
    }

    public static BlockEntityType<?> getBlockEntity(String id) {
        return blockEntities.get(id);
    }

    public static RebornRecipeType<?> getBlockRecipe(String id) {
        return blockRecipes.get(id);
    }
}