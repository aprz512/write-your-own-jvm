package write.your.own.jvm.classfile.constantpool;

/**
 * Constant Type	                Value
 * CONSTANT_Class	                7
 * CONSTANT_Fieldref	            9
 * CONSTANT_Methodref	            10
 * CONSTANT_InterfaceMethodref	    11
 * CONSTANT_String	                8
 * CONSTANT_Integer	                3
 * CONSTANT_Float	                4
 * CONSTANT_Long	                5
 * CONSTANT_Double	                6
 * CONSTANT_NameAndType	            12
 * CONSTANT_Utf8	                1
 * CONSTANT_MethodHandle	        15
 * CONSTANT_MethodType	            16
 * CONSTANT_InvokeDynamic	        18
 */
public interface ConstantInfo {
    int CONST_TAG_CLASS = 7;
    int CONST_TAG_FIELD_REF = 9;
    int CONST_TAG_METHOD_REF = 10;
    int CONST_TAG_INTERFACE_METHOD_REF = 11;
    int CONST_TAG_STRING = 8;
    int CONST_TAG_INTEGER = 3;
    int CONST_TAG_FLOAT = 4;
    int CONST_TAG_LONG = 5;
    int CONST_TAG_DOUBLE = 6;
    int CONST_TAG_NAME_AND_TYPE = 12;
    int CONST_TAG_UTF8 = 1;
    int CONST_TAG_METHOD_HANDLE = 15;
    int CONST_TAG_METHOD_TYPE = 16;
    int CONST_TAG_INVOKE_DYNAMIC = 18;

    // only part types implement this method
    // should be carefully
    String getValue();

}