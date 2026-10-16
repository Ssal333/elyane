package ssal333.elyane.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class IncreasingEffect extends MobEffect {
    protected IncreasingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe9b8b3);
    }

    static int count = 0;

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob.getSpeed() > 0 && mob.isSprinting()){

            int amplifier = 1;


            if (count < 150){
                count += 1;
                amplifier = count;
            }
            else{
                amplifier = 150;
            }

            MobEffectInstance speed = new MobEffectInstance(MobEffects.SPEED, 2, amplifier, false, false, false);
            MobEffectInstance saturation = new MobEffectInstance(MobEffects.SATURATION, 2, 100, false, false, false);
            mob.addEffect(speed);
            mob.addEffect(saturation);


        } else{
            count = 0;
        }


        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        IncreasingEffect.count = count;
    }
}


