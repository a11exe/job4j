package ru.job4j.isp;

import java.util.*;

/**
 * Menu base class
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public abstract class Menu {

    private final String name;
    private final List<Menu> menuList;
    private Menu parent;
    private int num;

    public Menu(String name) {
        this.name = name;
        this.menuList = new ArrayList<>();
        this.num = 1;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void addMenuItem(Menu menu) {
        this.menuList.add(menu);
        menu.setNum(this.menuList.size());
        menu.setParent(this);
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Menu getParent() {
        return parent;
    }

    /**
     * get list of all children ordered hierarchy
     * @return list with all children
     */
    public List<Menu> getSubMenuTree() {
        SortedSet<Menu> result = new TreeSet<>((a, b) -> (a.getFullNumber(".").compareTo(b.getFullNumber("."))));
        Queue<Menu> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Menu menu = queue.poll();
            result.add(menu);
            menu.getMenuList().forEach(queue::offer);
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
        Menu par = getParent();
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
