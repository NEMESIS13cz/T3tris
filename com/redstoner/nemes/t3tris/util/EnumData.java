package com.redstoner.nemes.t3tris.util;

public enum EnumData {

	STRING, BOOLEAN, BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE;
	
	public static EnumData get(String type) throws DataException {
		switch(type.toLowerCase()) {
		case "string": return EnumData.STRING;
		case "boolean": return EnumData.BOOLEAN;
		case "byte": return EnumData.BYTE;
		case "short": return EnumData.SHORT;
		case "integer": return EnumData.INTEGER;
		case "long": return EnumData.LONG;
		case "float": return EnumData.FLOAT;
		case "double": return EnumData.DOUBLE;
		default: throw new DataException();
		}
	}
}
