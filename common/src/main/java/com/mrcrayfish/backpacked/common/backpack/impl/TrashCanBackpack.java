package com.mrcrayfish.backpacked.common.backpack.impl;

import com.mrcrayfish.backpacked.Constants;
import com.mrcrayfish.backpacked.common.backpack.Backpack;
import com.mrcrayfish.backpacked.common.tracker.IProgressTracker;
import com.mrcrayfish.backpacked.common.tracker.ProgressFormatters;
import com.mrcrayfish.backpacked.common.tracker.impl.CountProgressTracker;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Author: MrCrayfish
 */
public class TrashCanBackpack extends Backpack
{
    public static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, "trash_can");

    public TrashCanBackpack()
    {
        super(Optional.empty());
    }

    @Nullable
    @Override
    public IProgressTracker createProgressTracker(ResourceLocation backpackId)
    {
        return new CountProgressTracker(100, ProgressFormatters.USED_X_TIMES);
    }
}
