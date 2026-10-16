package ssal333.elyane.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.effect.ModEffects;
import ssal333.elyane.item.ModItems;

public class DamageEvent implements ServerLivingEntityEvents.AllowDamage{



    @Override
    public boolean allowDamage(LivingEntity livingEntity, DamageSource damageSource, float v) {
        ItemStack equipmentAsBoots = livingEntity.getItemBySlot(EquipmentSlot.FEET);
        if (!livingEntity.level().isClientSide() && (livingEntity.is(EntityTypes.PLAYER)||livingEntity.is(EntityTypes.ARMOR_STAND)) && equipmentAsBoots.is(ModItems.FIRE_BOOTS)){

            int chargeCount = 0;

            if (damageSource.is(DamageTypes.HOT_FLOOR) && livingEntity.hurtTime == 0 && equipmentAsBoots.has(ModComponents.CHARGE_COMPONENT)){
                chargeCount = equipmentAsBoots.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
                equipmentAsBoots.set(ModComponents.CHARGE_COMPONENT, chargeCount+1);

            }


            if (chargeCount >= 4){
                MobEffectInstance mobEffectInstance = new MobEffectInstance(ModEffects.INCREASING, 20*20);
                livingEntity.addEffect(mobEffectInstance);
                equipmentAsBoots.set(ModComponents.CHARGE_COMPONENT, 0);
            }
        }


        return true;
    }
}
