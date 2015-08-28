package com.redstoner.nemes.t3tris.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DataFile {

	public static final char SEGMENT_SEPARATOR = '	';
	
	private HashMap<String, Object[]> map = new HashMap<String, Object[]>();
	
	public DataFile() {
		
	}
	
	public DataFile(String path, String name, DataFile defaultContent) {
		if (!loadFile(path, name, defaultContent)) {
			map.clear();
		}
	}
	
	private boolean loadFile(String path, String name, DataFile defaultFile) {
		File f = new File(path + "/" + name);
		if (!f.exists()) {
			if (!defaultFile.saveFile(path, name)) {
				return false;
			}
		}
		ArrayList<String> data;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			data = new ArrayList<String>();
			String buffer;
			while ((buffer = reader.readLine()) != null) {
				data.add(buffer);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		for (String s : data) {
			String key = s.substring(0, s.indexOf(DataFile.SEGMENT_SEPARATOR));
			String temp = s.substring(s.indexOf(DataFile.SEGMENT_SEPARATOR) + 1);
			String type = temp.substring(0, temp.indexOf(DataFile.SEGMENT_SEPARATOR));
			String value = temp.substring(temp.indexOf(DataFile.SEGMENT_SEPARATOR) + 1);
			try {
				addSpecifiedObject(key, (Object)value, EnumData.get(type));
			} catch (DataException e) {
				System.err.println("DataFile contains invalid types! " + type);
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public boolean saveFile(String path, String name) {
		File f = new File(path + "/" + name);
		File dir = new File(path);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				return false;
			}
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			Set<String> set = map.keySet();
			Iterator<String> i = set.iterator();
			while (i.hasNext()) {
				String key = i.next();
				String data = key + DataFile.SEGMENT_SEPARATOR + map.get(key)[1].toString() + DataFile.SEGMENT_SEPARATOR + String.valueOf(map.get(key)[0]);
				writer.write(data + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void addString(String key, String value) {
		map.put(key, new Object[] {value, EnumData.STRING});
	}
	
	public void addBoolean(String key, boolean value) {
		map.put(key, new Object[] {value, EnumData.BOOLEAN});
	}
	
	public void addByte(String key, byte value) {
		map.put(key, new Object[] {value, EnumData.BYTE});
	}
	
	public void addShort(String key, short value) {
		map.put(key, new Object[] {value, EnumData.SHORT});
	}
	
	public void addInteger(String key, int value) {
		map.put(key, new Object[] {value, EnumData.INTEGER});
	}
	
	public void addLong(String key, long value) {
		map.put(key, new Object[] {value, EnumData.LONG});
	}
	
	public void addFloat(String key, float value) {
		map.put(key, new Object[] {value, EnumData.FLOAT});
	}
	
	public void addDouble(String key, double value) {
		map.put(key, new Object[] {value, EnumData.DOUBLE});
	}
	
	public void addSpecifiedObject(String key, Object value, EnumData type) {
		map.put(key, new Object[] {value, type});
	}
	
	
	
	public String getString(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.STRING) {
			return (String)objects[0];
		}
		throw new DataException();
	}
	
	public boolean getBoolean(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.STRING) {
			return Boolean.parseBoolean((String) objects[0]);
		}
		throw new DataException();
	}
	
	public byte getByte(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.BYTE) {
			return Byte.parseByte((String) objects[0]);
		}
		throw new DataException();
	}
	
	public short getShort(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.SHORT) {
			return Short.parseShort((String) objects[0]);
		}
		throw new DataException();
	}
	
	public int getInteger(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.INTEGER) {
			return Integer.parseInt((String) objects[0]);
		}
		throw new DataException();
	}
	
	public long getLong(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.LONG) {
			return Long.parseLong((String) objects[0]);
		}
		throw new DataException();
	}
	
	public float getFloat(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.FLOAT) {
			return Float.parseFloat((String) objects[0]);
		}
		throw new DataException();
	}
	
	public double getDouble(String key) throws DataException {
		Object[] objects = map.get(key);
		if (objects[1] == EnumData.DOUBLE) {
			return Double.parseDouble((String) objects[0]);
		}
		throw new DataException();
	}
	
	public Object getObject(String key) {
		return map.get(key);
	}
}
