package classes;

import interfaces.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {

    public PreProcessorToLowerImpl() {
    }

    @Override
    public String preProcess(String str) {
        return str.toLowerCase();
    }
}
