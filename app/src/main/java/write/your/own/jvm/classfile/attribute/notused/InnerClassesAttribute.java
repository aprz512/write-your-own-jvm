package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;

/**
 * InnerClasses_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 number_of_classes;
 * {   u2 inner_class_info_index;
 * u2 outer_class_info_index;
 * u2 inner_name_index;
 * u2 inner_class_access_flags;
 * } classes[number_of_classes];
 * }
 */
public class InnerClassesAttribute extends AttributeInfo {
    private final int numberOfClasses;
    private final Clazz[] classes;

    public InnerClassesAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        numberOfClasses = reader.nextU2ToInt();
        classes = Clazz.readClasses(numberOfClasses, reader);
    }

    private static class Clazz {
        public final int innerClassInfoIndex;
        public final int outerClassInfoIndex;
        public final int innerNameIndex;
        public final int innerClassAccessFlags;

        public Clazz(ClassReader reader) {
            innerClassInfoIndex = reader.nextU2ToInt();
            outerClassInfoIndex = reader.nextU2ToInt();
            innerNameIndex = reader.nextU2ToInt();
            innerClassAccessFlags = reader.nextU2ToInt();
        }

        static Clazz[] readClasses(int classesCount, ClassReader reader) {
            Clazz[] classes = new Clazz[classesCount];
            for (int i = 0; i < classesCount; i++) {
                classes[i] = new Clazz(reader);
            }
            return classes;
        }

    }
}
