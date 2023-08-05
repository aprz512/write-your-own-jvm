package write.your.own.jvm.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @Test
    public void testNumUtil() {
        byte b = -128;
        int sb = NumUtil.byteToSignedInt(b);
        int usb = NumUtil.byteToUnsignedInt(b);
        assertEquals(sb, -128);
        assertEquals(usb, 0x80);
    }

    @Test
    public void testSign() {
        byte b = -1;
        System.out.println(b);
    }

    @Test
    public void testCast() {
        Object x = 2;
        assertEquals(2, (int)x);
    }

    @Test
    public void testParse() {
        int i = ByteUtil.byteToInt(new byte[]{-128, 0, 0, 0});
        assertEquals(i, Integer.MIN_VALUE);
    }

    @Test
    public void testSolution() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        reverseList(l1);
    }

    public ListNode reverseList(ListNode head) {

        ListNode dummy = new ListNode(-1, head);
        ListNode tmp;
        ListNode cur = head;

        while(cur != null && cur.next != null) {
            tmp = cur.next;

            cur.next = tmp.next;
            dummy.next = tmp;
            tmp.next = cur;

            cur = cur.next;
        }

        return dummy.next;

    }

     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

}
