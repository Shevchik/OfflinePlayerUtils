/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package offlineplayerutils.internal.itemstack;

import offlineplayerutils.simplenbt.NBTTagCompound;
import offlineplayerutils.simplenbt.NBTTagType;

import org.bukkit.inventory.ItemStack;

public class ItemStackSerializer {

	public static ItemStack createItemStack(NBTTagCompound compound) {
		return new WrappedItemStack(compound);
	}

	public static NBTTagCompound saveToNBT(ItemStack itemstack) {
		if (itemstack instanceof WrappedItemStack) {
			return ((WrappedItemStack) itemstack).getTag();
		} else {
			return fromBukkit(itemstack);
		}
	}

	private static NBTTagCompound fromBukkit(ItemStack itemstack) {
		NBTTagCompound compound = (NBTTagCompound) NBTTagType.COMPOUND.create();
		WrappedItemStack wrappedstack = new WrappedItemStack(compound);
		wrappedstack.setType(itemstack.getType());
		wrappedstack.setAmount(itemstack.getAmount());
		wrappedstack.setDurability(itemstack.getDurability());
		return wrappedstack.getTag();
	}

}
