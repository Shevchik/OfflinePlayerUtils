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

package offlineplayerutils.internal.impl;

import java.util.UUID;

import offlineplayerutils.api.LocationInfo;
import offlineplayerutils.internal.LocationDataInterface;
import offlineplayerutils.simplenbt.NBTTagCompound;
import offlineplayerutils.simplenbt.NBTTagList;
import offlineplayerutils.simplenbt.NBTTagNumber;
import offlineplayerutils.simplenbt.NBTTagType;

import org.bukkit.OfflinePlayer;

public class LocationData implements LocationDataInterface {

	@SuppressWarnings("unchecked")
	@Override
	public LocationInfo getLocation(OfflinePlayer player) {
		NBTTagCompound data = DataUtils.getData(player);
		UUID world = new UUID(data.getLong("WorldUUIDMost"), data.getLong("WorldUUIDLeast"));
		if (data.hasListOfNumberType("Pos")) {
			NBTTagList<NBTTagNumber<Number>> nbttaglist = (NBTTagList<NBTTagNumber<Number>>) data.get("Pos");
			if (nbttaglist.size() >= 3) {
				double x = nbttaglist.get(0).getValue().doubleValue();
				double y = nbttaglist.get(1).getValue().doubleValue();
				double z = nbttaglist.get(2).getValue().doubleValue();
				return new LocationInfo(world, x, y, z);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setLocation(OfflinePlayer player, LocationInfo location) {
		NBTTagCompound data = DataUtils.getData(player);
		data.setLong("WorldUUIDMost", location.getWorldUUID().getMostSignificantBits());
		data.setLong("WorldUUIDLeast", location.getWorldUUID().getLeastSignificantBits());
		NBTTagList<NBTTagNumber<Double>> nbttaglist = (NBTTagList<NBTTagNumber<Double>>) NBTTagType.LIST.create();
		NBTTagNumber<Double> xtag = (NBTTagNumber<Double>) NBTTagType.DOUBLE.create();
		xtag.setValue(location.getX());
		nbttaglist.add(xtag);
		NBTTagNumber<Double> ytag = (NBTTagNumber<Double>) NBTTagType.DOUBLE.create();
		ytag.setValue(location.getY());
		nbttaglist.add(ytag);
		NBTTagNumber<Double> ztag = (NBTTagNumber<Double>) NBTTagType.DOUBLE.create();
		ztag.setValue(location.getZ());
		nbttaglist.add(ztag);
		data.set("Pos", nbttaglist);
		DataUtils.saveData(player, data);
	}

}