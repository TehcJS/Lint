package io.github.hydos.lint.sound;

import io.github.hydos.lint.Lint;
import io.github.hydos.lint.mixin.SoundEventAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class Sounds {

    public static final SoundEvent KING_TATER = new SoundEvent(Lint.id("music.king_tater_boss_theme"));
    public static final SoundEvent I509 = new SoundEvent(Lint.id("music.i509_boss_theme"));
    public static final SoundEvent LEX_MANOS = new SoundEvent(Lint.id("music.lex_manos_boss_theme"));
    public static final SoundEvent DUNGEON = new SoundEvent(Lint.id("music.clandestine"));
    public static final SoundEvent OBOE = new SoundEvent(Lint.id("music.oboe"));
    public static final SoundEvent MYSTICAL_FOREST = new SoundEvent(Lint.id("music.mystical_forest"));

    public static final SoundEvent ADVANCEMENT = new SoundEvent(Lint.id("player.advancement"));

    public static final MusicSound KING_TATER_LOOP = MusicType.createIngameMusic(KING_TATER);
    public static final MusicSound I509_LOOP = MusicType.createIngameMusic(I509);
    public static final MusicSound LEX_MANOS_LOOP = MusicType.createIngameMusic(LEX_MANOS);

    public static void register(){
        Registry.register(Registry.SOUND_EVENT, ((SoundEventAccessor) KING_TATER).getId(), KING_TATER);
        Registry.register(Registry.SOUND_EVENT, ((SoundEventAccessor) I509).getId(), I509);
        Registry.register(Registry.SOUND_EVENT, ((SoundEventAccessor) LEX_MANOS).getId(), LEX_MANOS);
        Registry.register(Registry.SOUND_EVENT, ((SoundEventAccessor) MYSTICAL_FOREST).getId(), MYSTICAL_FOREST);
    }
}
