package com.gangsin.component;

import java.util.HashMap;
import java.util.Map;

public class MapParamCollector{

    Map<Object, Object> map = new HashMap<Object, Object>();

    public Object get(Object key){
        return map.get(key);
    }

    public Object put(Object key, Object value){
        return map.put(key, value);
    }
    
    public Map<Object, Object> getMap(){
    	return this.map;
    }

    public void putMap(Map map){
    	this.map.putAll(map);;
    }

    public String toString() {
        return map.toString();
    }
}