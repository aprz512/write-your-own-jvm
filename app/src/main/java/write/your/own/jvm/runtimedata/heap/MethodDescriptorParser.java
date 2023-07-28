package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class MethodDescriptorParser {

    private String raw;
    private int offset;

    public MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        startParams();
        List<String> paramTypes = parseParamTypes();
        endParams();
        String returnType = parseReturnType();
        finish();

        return new MethodDescriptor(paramTypes, returnType);
    }

    private void startParams() {
        if (readChar() != '(') {
            throw new MyJvmException("parse error : " + raw + ", at " + offset);
        }
    }

    private List<String> parseParamTypes() {
        List<String> paramsType = new ArrayList<>();
        while (true) {
            String type = parseFieldType();
            if (type.equals("")) {
                break;
            } else {
                paramsType.add(type);
            }
        }
        return paramsType;
    }

    private void endParams() {
        if (readChar() != ')') {
            throw new MyJvmException("parse error : " + raw + ", at " + offset);
        }
    }

    private String parseReturnType() {
        if (readChar() == 'V') {
            return "V";
        }
        unreadChar();
        String fieldType = parseFieldType();
        if (StringUtil.isEmpty(fieldType)) {
            throw new MyJvmException("parse error : " + raw + ", at " + offset);
        }
        return fieldType;
    }

    private void finish() {
        if (offset != raw.length()) {
            throw new MyJvmException("parse error : " + raw + ", at " + offset);
        }
    }

    private char readChar() {
        char ch = raw.charAt(offset);
        offset++;
        return ch;
    }

    private void unreadChar() {
        offset--;
    }

    private String parseFieldType() {
        switch (readChar()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            default:
                unreadChar();
                return "";
        }
    }

    private String parseArrayType() {
        int arrStart = offset - 1;
        parseFieldType();
        int arrEnd = offset;
        return raw.substring(arrStart, arrEnd);
    }

    private String parseObjectType() {
        int semicolonIndex = raw.substring(offset).indexOf(";");
        if (semicolonIndex == -1) {
            throw new MyJvmException("parse error : " + raw + ", at " + offset);
        } else {
            int start = offset - 1;
            int end = semicolonIndex + offset + 1;
            offset = end;
            return raw.substring(start, end);
        }
    }

}
