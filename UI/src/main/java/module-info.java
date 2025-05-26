import dk.sdu.cbse.common.services.IHUDPluginService;

module UI {
    uses dk.sdu.cbse.common.scoring.ScoringSPI;
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;

    requires spring.context;
    requires spring.beans;
    requires CommonScore;

    exports dk.sdu.cbse.UI.Score;
    opens dk.sdu.cbse.UI.Score to spring.core, spring.beans, spring.context;


    provides IHUDPluginService with dk.sdu.cbse.UI.Score.ScorePlugin;
}