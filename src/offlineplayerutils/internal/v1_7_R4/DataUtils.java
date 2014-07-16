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

package offlineplayerutils.internal.v1_7_R4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.minecraft.server.v1_7_R4.NBTCompressedStreamTools;
import net.minecraft.server.v1_7_R4.NBTTagCompound;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class DataUtils {

	public static NBTTagCompound getData(OfflinePlayer player) throws FileNotFoundException {
        File file = new File(Bukkit.getWorlds().get(0).getWorldFolder().getAbsolutePath() + File.separator + "playerdata" + File.separator + player.getUniqueId() + ".dat");
        if (file.exists()) {
            return NBTCompressedStreamTools.a((new FileInputStream(file)));
        }
        return null;
    }

	public static void saveData(OfflinePlayer player, NBTTagCompound data) throws FileNotFoundException {
		File file1 = new File(Bukkit.getWorlds().get(0).getWorldFolder().getAbsolutePath() + File.separator + "playerdata" + File.separator + player.getUniqueId() + ".dat.tmp");
		File file2 = new File(Bukkit.getWorlds().get(0).getWorldFolder().getAbsolutePath() + File.separator + "playerdata" + File.separator + player.getUniqueId() + ".dat");
		NBTCompressedStreamTools.a(data, (new FileOutputStream(file1)));
		if (file2.exists()) {
			file2.delete();
		}
		file1.renameTo(file2);
    }

}