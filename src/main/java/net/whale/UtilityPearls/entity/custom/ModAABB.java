package net.whale.UtilityPearls.entity.custom;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModAABB extends AABB {
    public ModAABB(Vec3 p_82302_, Vec3 p_82303_) {
        super(p_82302_, p_82303_);
    }
    public ModAABB(double p_82295_, double p_82296_, double p_82297_, double p_82298_, double p_82299_, double p_82300_) {
        super(p_82295_,p_82296_,p_82297_,p_82298_,p_82299_,p_82300_);
    }

    public Optional<Vec3> isPointInEntity(Vec3 point){
        if(this.maxX < point.x || this.minX > point.x)return Optional.empty();
        if(this.maxY < point.y || this.minY > point.y)return Optional.empty();
        if(this.maxZ < point.z || this.minZ > point.z)return Optional.empty();
        return Optional.of(point);
    }
    public List<Vec3> getPossibleHitPoints(Vec3 start,Vec3 end){
        Vec3 way = start.subtract(end);
        List<Vec3> pointList = new ArrayList<>();
        for (int i = 0; i<10;i++) {
            pointList.add(start.add(way.scale(0.1 * i)));
        }
        return pointList;
    }

    @Override
    public AABB inflate(double p_82378_, double p_82379_, double p_82380_) {
        double d0 = this.minX - p_82378_;
        double d1 = this.minY - p_82379_;
        double d2 = this.minZ - p_82380_;
        double d3 = this.maxX + p_82378_;
        double d4 = this.maxY + p_82379_;
        double d5 = this.maxZ + p_82380_;
        return new ModAABB(d0, d1, d2, d3, d4, d5);
    }
}
