package write.your.own.jvm.runtimedata.heap;

public class MyObject {

    private final MyClass myClass;
    private final InstanceFields instanceFields;

    public MyObject(MyClass myClass) {
        this.myClass = myClass;
        this.instanceFields = new InstanceFields(myClass.getInstanceSlotCount());
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public InstanceFields getInstanceFields() {
        return instanceFields;
    }

//    public void set(String name, String descriptor, MyObject object) {
//        MyClass currentClass = myClass;
//        while (currentClass != null) {
//            MyField[] fields = currentClass.getFields();
//            for (MyField field : fields) {
//
//            }
//        }
//    }


}
