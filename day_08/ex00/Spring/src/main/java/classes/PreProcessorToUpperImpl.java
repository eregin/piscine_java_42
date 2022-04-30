package classes;

import interfaces.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    public PreProcessorToUpperImpl() {
    }

    @Override
    public String preProcess(String str) {
        return str.toUpperCase();
    }
}
