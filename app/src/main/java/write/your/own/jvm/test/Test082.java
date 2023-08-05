package write.your.own.jvm.test;

public class Test082 {

    public static void main(String[] args) {
        double[][] class_score = {{100, 99, 99}, {100, 98, 97}, {100, 100, 99.5}, {99.5, 99, 98.5}};
        for (int i = 0; i < class_score.length; i++) {    //遍历行
            for (int j = 0; j < class_score[i].length; j++) {
                System.out.println("class_score[" + i + "][" + j + "]=" + class_score[i][j]);
            }
        }
    }

}
