package java_essential.my_exception;

/**
 * @author: luzj
 * @date: 2018/2/13
 * @description: 尝试使用try-with-resource
 * 这个机制是在jdk7加的，凡是实现autoCloseable的接口都可以使用这个机制
 */
public class TasteTryWithResource {


    public static void main(String[] args) {
//        new TasteTryWithResource().testRes();
        new TasteTryWithResource().testCloseManyResource();
    }

    /**
     * 测试Resource内部类
     */
    public void testRes(){
        try(Resource res = new Resource();) {
            res.doSome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试使用try-witg-resource关闭多个资源
     */
    public void testCloseManyResource(){
        //todo try子句包含多个资源，最后关闭从后向前依次关闭资源
        try(ResourceSome some = new ResourceSome();ResourceOther other = new ResourceOther();) {
            some.doSome();
            other.doOther();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义资源类A
     */
    class ResourceSome implements AutoCloseable{
    public void doSome(){
        System.out.println("do something");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close some resource");
    }
    }

    /**
     * 定义资源类B
     */
    class ResourceOther implements AutoCloseable{
public void doOther(){
    System.out.println("do other thing");
}
    @Override
    public void close() throws Exception {
        System.out.println("close other resource");
    }
}

    /**
     * 内部类，资源类——实现了可自动关闭接口
     */
    class Resource implements AutoCloseable{
    void doSome(){
        System.out.println("do something");
    }

    /**
     * 关闭资源的时候，运行该方法
     * @throws Exception 关闭资源出现异常时
     */
    @Override
    public void close() throws Exception {
        System.out.println("resource is closed");
    }
}

}
