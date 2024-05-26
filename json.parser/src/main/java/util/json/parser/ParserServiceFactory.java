package util.json.parser;

import util.json.parser.impl.Parser;


public class ParserServiceFactory {
    public static ParserService getParserService() {
        return getParserService_old();
    }
    public static ParserService getParserService_old() {
        return new Parser();
    }


}