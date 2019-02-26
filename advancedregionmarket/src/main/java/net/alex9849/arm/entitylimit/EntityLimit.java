package net.alex9849.arm.entitylimit;

import net.alex9849.arm.Messages;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

public class EntityLimit {
    private EntityType entityType;
    private int softlimit;
    private int hardlimit;
    private int pricePerExtraEntity;

    public EntityLimit(EntityType entityType, int softlimit, int hardlimit, int pricePerExtraEntity) {
        if(softlimit < 0) {
            softlimit = softlimit * (-1);
        }
        if(hardlimit < 0) {
            hardlimit = hardlimit * (-1);
        }
        if(hardlimit < softlimit) {
            hardlimit = softlimit;
        }
        if(pricePerExtraEntity < 0) {
            pricePerExtraEntity = 0;
        }
        this.entityType = entityType;
        this.softlimit = softlimit;
        this.hardlimit = hardlimit;
        this.pricePerExtraEntity = pricePerExtraEntity;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public int getSoftLimit(int expansion) {
        if((this.softlimit + expansion) >= 0) {
            return this.softlimit + expansion;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int getHardLimit() {
        return this.hardlimit;
    }

    public int getPricePerExtraEntity() {
        return this.pricePerExtraEntity;
    }

    public String getConvertedMessage(String message, List<Entity> entities, int entityExpansion) {
        message = message.replace("%entitytype%", this.getEntityType().name());
        message = message.replace("%actualentities%", EntityLimitGroup.filterEntitys(entities, this.getEntityType()).size() + "");
        message = message.replace("%softlimitentities%", EntityLimitGroup.intToLimitString(this.getSoftLimit(entityExpansion)));
        message = message.replace("%hardlimitentities%", EntityLimitGroup.intToLimitString(this.getHardLimit()));
        message = message.replace("%priceperextraentity%", this.getPricePerExtraEntity() + "");
        message = message.replace("%currency%", Messages.CURRENCY);
        return message;
    }
}
