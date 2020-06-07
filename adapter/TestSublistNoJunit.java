package adapter;

public class TestSublistNoJunit {
    public static void main(String[] args) {
        ListAdapter l = new ListAdapter();
        for(int i = 0; i < 10; i++) {
			l.add(new Object());
		}
        ListAdapter sub = (ListAdapter) l.subList(3, 8);
        System.out.println(sub.size());
        sub.add(0, new Object());
        System.out.println(sub.get(0));
    }
}