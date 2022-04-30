package app;

import interfaces.Printer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithDateTime", Printer.class);
        printer.print("Hello!");
//        PreProcessor preProcessor = new PreProcessorToLowerImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithDateTimeImpl printer = new PrinterWithDateTimeImpl(renderer);
//        printer.print("HELLO!");
    }
}
