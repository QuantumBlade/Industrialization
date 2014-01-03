package dmillerw.industrialization.core.ore;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dylan Miller on 1/2/14
 */
public class OreHandler {

    public static final OreHandler INSTANCE = new OreHandler();

    private Map<String, OreWrapper> registeredOres = new HashMap<String, OreWrapper>();

    public List<OreWrapper> getRegisteredOres() {
        List<OreWrapper> ores = new ArrayList<OreWrapper>();
        for (Map.Entry<String, OreWrapper> entry : registeredOres.entrySet()) {
            ores.add(entry.getValue());
        }
        return ores;
    }

    private OreWrapper getOre(String oreTag) {
        if (!registeredOres.containsKey(oreTag)) {
            OreWrapper ore = new OreWrapper(oreTag);
            registeredOres.put(oreTag, ore);
        }
        return registeredOres.get(oreTag);
    }

    private void replace(OreWrapper ore) {
        registeredOres.put(ore.oreTag, ore);
    }

    public void addVanillaBlocks() {
        for (String ore : OreDictionary.getOreNames()) {
            for (ItemStack oreStack : OreDictionary.getOres(ore)) {
                handleOre(ore, oreStack);
            }
        }
    }

    @ForgeSubscribe
    public void oreDictionaryEvent(OreDictionary.OreRegisterEvent event) {
        handleOre(event.Name, event.Ore);
    }

    public void handleOre(String oreTag, ItemStack oreStack) {
        if (oreTag.startsWith("ore")) {
            String baseTag = oreTag.substring("ore".length());
            System.out.println("Found BLOCK for " + baseTag);
            OreWrapper ore = getOre(baseTag);
            ore.addBlock(oreStack);
            replace(ore);
        } else if (oreTag.startsWith("dust") && !(oreTag.contains("Tiny"))) {
            String baseTag = oreTag.substring("dust".length());
            OreWrapper ore = getOre(baseTag);

            if (ore.getDust() == null) {
                System.out.println("Found DUST for " + baseTag);
                ore.setDust(oreStack);
                replace(ore);
            }
        }
    }

}
