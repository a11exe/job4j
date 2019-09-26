package ru.job4j.game.utils;

import ru.job4j.game.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.09.2019
 */
public class LoadUtil {

    public List<Hero> loadHeroes(Group defaultGroup) {
        List<Hero> heroes = new ArrayList<>();

        // ELF
        heroes.addAll(getELFs(defaultGroup));
        // MEN
        heroes.addAll(getMENs(defaultGroup));
        // ORC
        heroes.addAll(getORCs(defaultGroup));
        // ZOMBIE
        heroes.addAll(getZOMBIEs(defaultGroup));

        return heroes;
    }

    private List<Hero> getELFs(Group defaultGroup) {
        List<Hero> heroes = new ArrayList<>();
        List<Action> wizardActions = new ArrayList<>();
        wizardActions.add(new ActionImpl(
                "наложение улучшения на персонажа своего отряда",
                0,
                true,
                Group.EXTRA,
                List.of(Group.REGULAR, Group.DISEASED)));
        wizardActions.add(new ActionImpl(
                "нанесение урона персонажу противника магией на 10 HP",
                10,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ELF, Skill.WIZARD, wizardActions, defaultGroup, 100));

        List<Action> archerActions = new ArrayList<>();
        archerActions.add(new ActionImpl(
                "стрелять из лука (нанесение урона 7 HP)",
                7,
                false,
                null,
                null));
        archerActions.add(new ActionImpl(
                "атаковать противника (нанесение урона 3 HP)",
                3,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, defaultGroup, 100));

        List<Action> fighterActions = new ArrayList<>();
        fighterActions.add(new ActionImpl(
                "атаковать мечом (нанесение урона 15 HP)",
                15, false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ELF, Skill.FIGHTER, fighterActions, defaultGroup, 100));
        return heroes;
    }

    private List<Hero> getMENs(Group defaultGroup) {
        List<Hero> heroes = new ArrayList<>();
        List<Action> wizardActions;
        wizardActions = new ArrayList<>();
        wizardActions.add(new ActionImpl(
                "наложение улучшения на персонажа своего отряда",
                0,
                true,
                Group.EXTRA,
                List.of(Group.REGULAR, Group.DISEASED)));
        wizardActions.add(new ActionImpl(
                "атаковать магией (нанесение урона 4 HP)",
                4,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.MEN, Skill.WIZARD, wizardActions, defaultGroup, 100));


        List<Action> archerActions = new ArrayList<>();
        archerActions.add(new ActionImpl(
                "стрелять из арбалета (нанесение урона 5 HP)",
                5,
                false,
                null,
                null));
        archerActions.add(new ActionImpl(
                "атаковать (нанесение урона 3 HP)",
                3,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.MEN, Skill.ARCHER, archerActions, defaultGroup, 100));

        List<Action> fighterActions = new ArrayList<>();
        fighterActions.add(new ActionImpl(
                "атаковать мечом (нанесение урона 18 HP)",
                18,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.MEN, Skill.FIGHTER, fighterActions, defaultGroup, 100));

        return heroes;
    }

    private List<Hero> getORCs(Group defaultGroup) {
        List<Hero> heroes = new ArrayList<>();
        List<Action> wizardActions = new ArrayList<>();
        wizardActions.add(new ActionImpl(
                "наложение улучшения на персонажа своего отряда",
                0,
                true,
                Group.EXTRA,
                List.of(Group.REGULAR, Group.DISEASED)));
        wizardActions.add(new ActionImpl(
                "наложение проклятия (снятие улучшения с персонажа противника для следующего хода)",
                0,
                false,
                Group.REGULAR,
                List.of(Group.EXTRA)));
        heroes.add(new HeroImpl(Race.ORC, Skill.WIZARD, wizardActions, defaultGroup, 100));

        List<Action> archerActions = new ArrayList<>();
        archerActions.add(new ActionImpl(
                "стрелять из лука (нанесение урона 3 HP)",
                3,
                false,
                null,
                null));
        archerActions.add(new ActionImpl(
                "удар клинком (нанесение урона 2 HP)",
                2,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ORC, Skill.ARCHER, archerActions, defaultGroup, 100));

        List<Action> fighterActions = new ArrayList<>();
        fighterActions.add(new ActionImpl(
                "атака дубиной (нанесение урона 20 HP)",
                20,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ORC, Skill.FIGHTER, fighterActions, defaultGroup, 100));

        return heroes;
    }

    private List<Hero> getZOMBIEs(Group defaultGroup) {
        List<Hero> heroes = new ArrayList<>();
        List<Action> wizardActions = new ArrayList<>();
        wizardActions.add(new ActionImpl(
                "наслать недуг (уменьшение силы урона персонажа противника на 50% на один ход)",
                0,
                false,
                Group.DISEASED,
                List.of(Group.REGULAR, Group.EXTRA)));
        heroes.add(new HeroImpl(Race.ZOMBIE, Skill.WIZARD, wizardActions, defaultGroup, 100));

        List<Action> archerActions = new ArrayList<>();
        archerActions.add(new ActionImpl(
                "стрелять из лука (нанесение урона 4 HP)",
                4,
                false,
                null,
                null));
        archerActions.add(new ActionImpl(
                "атаковать (нанесение урона 2 HP)",
                2,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ZOMBIE, Skill.ARCHER, archerActions, defaultGroup, 100));

        List<Action> fighterActions = new ArrayList<>();
        fighterActions.add(new ActionImpl(
                "удар копьем (нанесение урона 18 HP)",
                18,
                false,
                null,
                null));
        heroes.add(new HeroImpl(Race.ZOMBIE, Skill.FIGHTER, fighterActions, defaultGroup, 100));

        return heroes;
    }

}
