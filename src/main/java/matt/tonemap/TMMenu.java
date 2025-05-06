package matt.tonemap;

import java.util.function.Function;
import java.util.function.Supplier;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;

public class TMMenu implements ModMenuApi{
    @Override
    public String getModId() {
        // Deprecated method that VSCode whines if I don't implement
        return "Tonemap";
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return screen -> getConfigScreen(screen).map(Supplier::get).orElse(null);
	}
}
