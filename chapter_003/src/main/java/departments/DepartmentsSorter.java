package departments;

import java.util.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 12.03.2019
 */
public class DepartmentsSorter {

    private final Comparator<String> comparator;
    private final Map<String, Department> departments;

    public DepartmentsSorter(Comparator<String> comparator) {
        this.comparator = comparator;
        this.departments = new TreeMap<>(this.comparator);
    }

    class Department {

        private final String name;
        private final Map<String, Department> subDepartments;

        public Department(String name, Comparator<String> comparator) {
            this.name = name;
            this.subDepartments = new TreeMap<>(comparator);
        }

        public String getName() {
            return name;
        }

        public Department addSubDepartment(String name, Comparator<String> comparator) {
            Department dep;
            if (this.subDepartments.containsKey(name)) {
                dep = this.subDepartments.get(name);
            } else {
                dep = new Department(name, comparator);
                this.subDepartments.put(name, dep);
            }
            return dep;
        }
    }

    private void loadDepartments(String[] departments) {

        Arrays.stream(departments).forEach(s -> {
            String[] parts = s.split("\\\\");
            if (parts.length > 0) {
                String name = parts[0];
                Department dep;
                if (this.departments.containsKey(name)) {
                    dep = this.departments.get(name);
                } else {
                    dep = new Department(name, this.comparator);
                    this.departments.put(name, dep);
                }
                for (int i = 1; i < parts.length; i++) {
                    dep = dep.addSubDepartment(parts[i], this.comparator);
                }
            }
        });
    }

    public String[] sort(String[] departments) {
        loadDepartments(departments);
        return departmentsToStringArray();
    }

    public void addStructure(Set<String> result, String str, Department department) {
        str = str.isEmpty() ? department.getName() : str + "\\" + department.getName();
        result.add(str);
        for (Department dep : department.subDepartments.values()
                ) {
            addStructure(result, str, dep);
        }
    }

    private String[] departmentsToStringArray() {
        Set<String> result = new LinkedHashSet<>();
        for (Department department:  this.departments.values()
                ) {
            addStructure(result, "", department);
        }
        return result.toArray(String[]::new);
    }

}
