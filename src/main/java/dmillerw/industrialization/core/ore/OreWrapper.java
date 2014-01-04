package dmillerw.industrialization.core.ore;

import dmillerw.industrialization.item.ItemGrinding;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan Miller on 1/2/14
 */
public class OreWrapper {

    public final String oreTag;

    private List<ItemStack> oreBlocks = new ArrayList<ItemStack>();
    private ItemStack grinding;
    private ItemStack dust;

    public OreWrapper(String oreTag) {
        this.oreTag = oreTag;
    }

    public void addBlock(ItemStack block) {
        oreBlocks.add(block);
    }

    public void generateGrinding() {
        this.grinding = ItemGrinding.getGrinding(oreTag);
    }

    public void setDust(ItemStack dust) {
        this.dust = dust;
    }

    public ItemStack[] getOreBlocks() {
        return oreBlocks.toArray(new ItemStack[oreBlocks.size()]);
    }

    public ItemStack getGrinding() {
        return grinding;
    }

    public ItemStack getDust() {
        return dust;
    }

}