package classes;

import interfaces.PreProcessor;
import interfaces.Renderer;

public class RendererStandardImpl implements Renderer {

    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void sendMessage(String str) {
        System.out.println(preProcessor.preProcess(str));
    }
}
