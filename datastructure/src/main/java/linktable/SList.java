package linktable;

import util.GeneratorRand;

import java.util.Random;

/**
 * @author luzj
 * @description: 单向链表基本操作 ： 遍历  长度  插入：{头部、中部、尾部}  删除：{头部、中部、尾部}  清空链表
 * @date 2018/4/26
 */
public class SList<T> {

    private SingleNode head;

    /**
     * 遍历打印链表
     *
     * @param headNode
     */
    public void traverse(SingleNode<T> headNode) {
        SingleNode currentNode = headNode;
        while (currentNode != null) {
            System.out.println(currentNode.getDate());
            currentNode = currentNode.getNext();
        }
    }

    /**
     * 遍历表
     *
     * @param headNode
     * @return 链表长度
     */
    public int listLength(SingleNode<T> headNode) {
        int length = 0;
        SingleNode currentNode = headNode;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    /**
     * 单向链表的插入(头部插入、中部插入、尾部插入)
     *
     * @param headNode     操作链表 表头节点
     * @param nodeToInsert 待插入节点
     * @param position     位置
     * @return 查完后链表
     */
    public SingleNode<T> insertInLinkedList(SingleNode<T> headNode, SingleNode<T> nodeToInsert, int position) {
        // 待插入链表为空
        if (headNode == null)
            return nodeToInsert;
        //带插入节点为空
        if (nodeToInsert == null)
            return headNode;

        int size = listLength(headNode);
        // 无效插入位置
        if (position > size + 1 || position < 1) {
            System.out.println("节点插入位置无效,有效位置为：1——" + ("" + 1));
            return headNode;
        }

        if (position == 1) {// 插在首位
            nodeToInsert.setNext(headNode);
            return nodeToInsert;
        } else {//插在中间或者尾部
            SingleNode<T> previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }
            SingleNode<T> currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            previousNode.setNext(nodeToInsert);
        }

        return headNode;
    }

    /**
     * 单向链表删除操作
     *
     * @param headNode 链表头节点
     * @param position 删除位置
     * @return 删除后节点
     */
    public SingleNode<T> deleteNodeFromLinkedList(SingleNode<T> headNode, int position) {
        int size = listLength(headNode);

        if (position > size || position < 1) {//位置越界
            System.out.println("位置越界...，position:1-->" + size);
            return headNode;
        }

        if (position == 1) { //删除表头节点
            SingleNode<T> currentNode = headNode.getNext();
            headNode = null;
            return currentNode;
        } else {//删除中间或者结尾节点
            SingleNode previousNode = headNode;
            int count = 1;
            while (count < position) {
                previousNode = previousNode.getNext();//待删除节点的前置节点
                count++;
            }

            SingleNode currentNode = previousNode.getNext();//待删除的节点
            if (currentNode.getNext() != null)
                previousNode.setNext(currentNode.getNext());
            currentNode = null;
        }

        return headNode;
    }

    /**
     * 删除整个单向链表
     *
     * @param headNode
     */
    public void deleteLikedList(SingleNode<T> headNode) {
        SingleNode auxilaryNode, iterator = headNode;
        while (iterator != null) {
            auxilaryNode = iterator.getNext();
            iterator = null; //对象失去引用，GC会自动处理
            iterator = auxilaryNode;
        }
    }


    /**
     * 初始化一个可用的链表
     *
     * @param data 节点数据数组
     * @return 新建链表的头节点
     */
    public SingleNode initNode(T[] data) {
        SingleNode<T> currentNode = null;//移动指针
        head = null;
        for (int i = 0; i < data.length; i++) {
            SingleNode<T> node = new SingleNode();
            node.setDate(data[i]);

            if (head == null) {
                head = node;
                currentNode = head;
                continue;
            }
            currentNode.setNext(node);
            currentNode = node;
        }
        return head;
    }


    public SingleNode getHead() {
        return head;
    }

    public void setHead(SingleNode head) {
        this.head = head;
    }


    public static void main(String[] args) {
        Random random = new Random();
        SList<Student> studentSList = new SList<>();
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            String name = GeneratorRand.generatorName();
            String teacher = GeneratorRand.generatorName();
            int age = random.nextInt(50) + 10;
            Student student = new Student(name, teacher, age);
            students[i] = student;
        }

        studentSList.initNode(students);

        studentSList.traverse(studentSList.head);
        System.out.println(studentSList.listLength(studentSList.head));

        SingleNode<Student> node = new SingleNode<>();
        Student s = new Student(GeneratorRand.generatorName(), GeneratorRand.generatorName(), GeneratorRand.generatorAge());
        System.err.println("\t新增节点：" + s);
        node.setDate(s);

        SingleNode x = studentSList.insertInLinkedList(studentSList.head, node, 8);
        studentSList.traverse(x);

        s = new Student(GeneratorRand.generatorName(), GeneratorRand.generatorName(), GeneratorRand.generatorAge());
        System.err.println("\t新增节点2：" + s);
        SingleNode<Student> node1 = new SingleNode<>();
        node1.setDate(s);

        SingleNode y = studentSList.insertInLinkedList(x, node1, 1);
        studentSList.traverse(y);

        System.out.println("删除：");
        SingleNode z = studentSList.deleteNodeFromLinkedList(y, 11);
        studentSList.traverse(z);


    }
}
