package edu.jimei.json.parser;

import edu.jimei.json.parser.impl.Parser;

public class ParserServiceFactory {
    public static ParserService getParserService() {
        return new Parser();  // 返回实现类的实例
    }
}