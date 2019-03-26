package ru.job4j.list;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class Node<T> {

    public final T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Обертка для прохода по цепочке назад
     */
    private class NodeWrapper {
        final Node<T> node;
        final NodeWrapper prev;

        public NodeWrapper(Node<T> node, NodeWrapper prev) {
            this.node = node;
            this.prev = prev;
        }
    }

    /**
     * Проверяет циклические ссылки
     * @param first элемент с которого начинать проверку
     * @return есть зацикленные ссылки
     */
    public boolean hasCycle(Node<T> first) {
        boolean cycle = false;
        NodeWrapper nodeWrapper = new NodeWrapper(first, null);
        while (nodeWrapper.node.next != null) {
            if (isCrossLink(nodeWrapper)) {
                cycle = true;
                break;
            }
            nodeWrapper = new NodeWrapper(nodeWrapper.node.next, nodeWrapper);
        }
        return cycle;
    }

    /**
     * Есть ссылки на текущий элемент среди предыдущих
     * @param nodeWrapper ссылка на элемент с которого начать проверку назад
     * @return есть ссылки на текущий элемент в предыдущих
     */
    private boolean isCrossLink(NodeWrapper nodeWrapper) {
        boolean crossLink = false;
        Node<T> node = nodeWrapper.node;
        while (nodeWrapper.prev != null) {
            nodeWrapper = nodeWrapper.prev;
            if (nodeWrapper.node.equals(node)) {
                crossLink = true;
                break;
            }
        }
        return crossLink;
    }
}
