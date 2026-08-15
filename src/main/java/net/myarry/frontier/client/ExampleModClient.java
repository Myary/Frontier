package net.myarry.frontier.client;

import net.myarry.frontier.FrontierMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Supplier;

@Mod(
        value = FrontierMod.MOD_ID,
        dist = Dist.CLIENT
)
public class ExampleModClient {

    public ExampleModClient(ModContainer container) {

        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (Supplier<IConfigScreenFactory>) () -> (modContainer, parent) -> ModConfigScreen.create(parent)
        );
    }
}