package me.hydos.lint.core;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface Sounds {

    Identifier KING_TATER_MUSIC_ID = new Identifier("lint:king_tater_boss");
    SoundEvent KING_TATER_SOUND_EVENT = new SoundEvent(KING_TATER_MUSIC_ID);

    static void onInitialize(){
        Registry.register(Registry.SOUND_EVENT, KING_TATER_MUSIC_ID, KING_TATER_SOUND_EVENT);
    }

}