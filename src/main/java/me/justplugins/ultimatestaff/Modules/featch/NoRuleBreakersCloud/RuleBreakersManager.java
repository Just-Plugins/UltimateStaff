package me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud;


import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud.CloudDataFetcher;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud.CloudRuleBreakers;

import java.util.List;

public class RuleBreakersManager {

    private final CloudDataFetcher cloud;

    public RuleBreakersManager(Main plugin) {
        this.cloud = new CloudDataFetcher(plugin);
    }

    public void onDisable() {
        cloud.onDisable();
    }

    public boolean isCloudOnline() {
        return cloud.isOnline();
    }

    public List<CloudRuleBreakers> getCloudAddons() {
        return cloud.getCurrent();
    }

}
