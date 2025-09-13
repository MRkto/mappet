package mchorse.mappet.network.server.npc;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mappet.network.common.npc.PacketNpcState;
import mchorse.mclib.network.ServerMessageHandler;
import mchorse.mclib.utils.OpHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerHandlerNpcState extends ServerMessageHandler<PacketNpcState>
{
    @Override
    public void run(EntityPlayerMP player, PacketNpcState message)
    {
        if ((Mappet.npcsToolOnlyOP.get() && !OpHelper.isPlayerOp(player)) || (Mappet.npcsToolOnlyCreative.get() && !player.capabilities.isCreativeMode)) {
            return;
        }
        Entity npc = player.world.getEntityByID(message.entityId);

        if (npc instanceof EntityNpc)
        {
            NpcState state = new NpcState();

            state.deserializeNBT(message.state);
            ((EntityNpc) npc).setState(state, true);
        }
    }
}