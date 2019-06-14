package ru.job4j.scripts;

import java.util.*;

/**
 * Get related scripts for loading requested.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.06.2019
 */
public class Scripts {

    /**
     * Get related scripts for loading requested.
     * @param ds - all scripts with dependencies.
     * @param scriptId - requested script.
     * @return - related scripts
     */
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {

        Set<Integer> result = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        addRelated(ds, scriptId, queue);

        while (!queue.isEmpty()) {
            Integer actualScriptId = queue.poll();
            // skip cross link
            if (!result.contains(actualScriptId) && !actualScriptId.equals(scriptId)) {
                result.add(actualScriptId);
                addRelated(ds, actualScriptId, queue);
            }
        }

        return new ArrayList<>(result);
    }

    private void addRelated(Map<Integer, List<Integer>> ds, Integer scriptId, Queue<Integer> queue) {
        Optional.ofNullable(ds.get(scriptId)).ifPresent(s -> s.forEach(queue::offer));
    }

}
