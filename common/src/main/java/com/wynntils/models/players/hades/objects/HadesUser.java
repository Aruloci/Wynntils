/*
 * Copyright © Wynntils 2022.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.players.hades.objects;

import com.wynntils.hades.protocol.packets.server.HSPacketUpdateMutual;
import com.wynntils.models.map.PoiLocation;
import com.wynntils.utils.colors.CommonColors;
import com.wynntils.utils.colors.CustomColor;
import com.wynntils.utils.type.CappedValue;
import java.util.UUID;

public class HadesUser {
    private final UUID uuid;
    private final String name;

    private boolean isPartyMember;
    private boolean isMutualFriend;
    private boolean isGuildMember;
    private float x, y, z;
    private PoiLocation poiLocation;
    private CappedValue health;
    private CappedValue mana;

    public HadesUser(HSPacketUpdateMutual packet) {
        uuid = packet.getUser();
        name = packet.getName();

        this.updateFromPacket(packet);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isPartyMember() {
        return isPartyMember;
    }

    public boolean isMutualFriend() {
        return isMutualFriend;
    }

    public boolean isGuildMember() {
        return isGuildMember;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public PoiLocation getMapLocation() {
        return poiLocation;
    }

    public CappedValue getHealth() {
        return health;
    }

    public CappedValue getMana() {
        return mana;
    }

    public void updateFromPacket(HSPacketUpdateMutual packet) {
        this.x = packet.getX();
        this.y = packet.getY();
        this.z = packet.getZ();
        this.poiLocation = new PoiLocation((int) x, (int) y, (int) z);

        this.health = new CappedValue(packet.getHealth(), packet.getMaxHealth());
        this.mana = new CappedValue(packet.getMana(), packet.getMaxMana());

        this.isPartyMember = packet.isPartyMember();
        this.isMutualFriend = packet.isMutualFriend();
        this.isGuildMember = packet.isGuildMember();
    }

    public CustomColor getRelationColor() {
        if (isPartyMember) return CommonColors.YELLOW;
        if (isMutualFriend) return CommonColors.GREEN;
        if (isGuildMember) return CommonColors.LIGHT_BLUE;

        return CustomColor.NONE;
    }
}
