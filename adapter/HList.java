package adapter;

public interface HList extends HCollection {
    public void add(int index, Object element);
    //public boolean add(Object o);
    //public boolean addAll(HCollection c);
    public boolean addAll(int index, HCollection c);
    //public void clear();
    //public boolean contains(Object o);
    //public boolean containsAll(HCollection c);
    //public boolean equals(Object o);
    public Object get(int index);
    //public int hashCode();
    public int indexOf(Object o);
    //public boolean isEmpty();
    //public HIterator iterator();
    public int lastIndexOf(Object o);
    public HListIterator listIterator();
    public HListIterator listIterator(int index);
    public Object remove(int index);
    //public boolean remove(Object o);
    //public boolean removeAll(HCollection c);
    //public boolean retainAll(HCollection c);
    public Object set(int index, Object element);
    //public int size();
    public HList subList(int fromIndex, int toIndex);
    //public Object[] toArray();
    //public Object[] toArray(Object[] a);
}