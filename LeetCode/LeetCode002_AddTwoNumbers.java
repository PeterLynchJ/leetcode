package LeetCodeRepo.leetcode.LeetCode;

public class LeetCode002_AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        @Override
        public String toString() {
            ListNode head = this;
            StringBuilder sb = new StringBuilder();
            while (head != null) {
                sb.append(head.val);
                head = head.next;
            }
            return sb.reverse().toString();
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(), head = res, p1 = l1, p2 = l2;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int sum = 0;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            sum += carry;
            carry = sum / 10;
            sum = sum % 10;
            head.next = new ListNode(sum);
            head = head.next;
        }
        if (carry != 0) {
           head.next = new ListNode(carry);
        }
        return res.next;
    }

    public static void main(String[] args) {
        LeetCode002_AddTwoNumbers driver = new LeetCode002_AddTwoNumbers();
        System.out.println(driver.addTwoNumbers(new ListNode(9), new ListNode(1)));
    }
}
