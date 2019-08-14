package ru.job4j.isp;

import java.util.*;

/**
 * SimpleMenu base class
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public class SimpleMenu {

    private final String name;
    private final List<SimpleMenu> menuList;
    private SimpleMenu parent;
    private int num;

    public SimpleMenu(String name) {
        this.name = name;
        this.menuList = new ArrayList<>();
        this.num = 1;
    }

    public String getName() {
        return name;
    }

    public List<SimpleMenu> getMenuList() {
        return menuList;
    }

    public void addMenuItem(SimpleMenu menu) {
        this.menuList.add(menu);
        menu.setNum(this.menuList.size());
        menu.setParent(this);
    }

    public void setParent(SimpleMenu parent) {
        this.parent = parent;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public SimpleMenu getParent() {
        return parent;
    }

    /**
     * get list of all children ordered hierarchy
     * @return list with all children
     */
    public List<SimpleMenu> getSubMenuTree() {
        SortedSet<SimpleMenu> result = new TreeSet<>((a, b) -> (a.getFullNumber(".").compareTo(b.getFullNumber("."))));
        Queue<SimpleMenu> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            SimpleMenu menu = queue.poll();
            result.add(menu);
            Objects.requireNonNull(menu).getMenuList().forEach(queue::offer);
        }
        return new ArrayList<>(result);
    }

    /**
     * Get string of numbers include all parents (4.1.1)
     * @param joiner - joiner
     * @return full number
     */
    public String getFullNumber(String joiner) {
        List<String> numChain = new ArrayList<>();
        numChain.add(String.valueOf(getNum()));
        SimpleMenu par = getParent();
        while (par != null) {
            numChain.add(String.valueOf(par.getNum()));
            par = par.getParent();
        }
        StringJoiner result = new StringJoiner(joiner);
        for (int i = numChain.size() - 1; i >= 0; i--) {
            result.add(numChain.get(i));
        }
        return result.toString();
    }

    /**
     * Get menu name
     * @return menu name
     */
    public String getPrintName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getPrintName();
    }
}
