package com.company.Module2.Lab1.Utility;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



    public class SimpleErrorHandler implements ErrorHandler {
        // метод для обработки предупреждений
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Строка " +e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
        // метод для обработки ошибок
        public void error(SAXParseException e) throws SAXException {
            System.out.println("Строка " +e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
        // метод для обработки критических ошибок
        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println("Строка " +e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
    }
