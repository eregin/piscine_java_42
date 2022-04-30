package classes;

import interfaces.Printer;
import interfaces.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;

    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        renderer.sendMessage(prefix + " " + str);
    }
}
