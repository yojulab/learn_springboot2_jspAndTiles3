package com.gangsin.component;

public interface PublicServiceInterface {

//	for Extra and View data
	Object getOtherAndSomething(Object dataMap);
	
//	for Form and View data
	Object getFormAndSomething(Object dataMap);

//	for one Record and View data
	Object getOneAndSomething(Object dataMap);

//	for Several Record and View data
	Object getListAndSomething(Object dataMap);

//	for Save One or Several Record and View data
	Object saveObjectAndSomething(Object dataMap);

//	for Modify One or Several Record and View data
	Object modifyObjectAndSomething(Object dataMap);

//	for Delete One or Several Record and View data
	Object deleteObjectAndSomething(Object dataMap);
//
/* Method Structure 
	//	for this.Method 
	public Object *(Object dataMap) { }
	//	for this.Method with Other Method 
	public Object *WithRelatedObject(Object dataMap) { }
*/
}
