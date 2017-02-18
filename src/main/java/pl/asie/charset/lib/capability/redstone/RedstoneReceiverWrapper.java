/*
 * Copyright (c) 2015-2016 Adrian Siekierka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.asie.charset.lib.capability.redstone;

import net.minecraftforge.common.capabilities.Capability;
import pl.asie.charset.api.wires.IBundledReceiver;
import pl.asie.charset.api.wires.IRedstoneReceiver;
import pl.asie.charset.lib.capability.Capabilities;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class RedstoneReceiverWrapper implements Function<List<IRedstoneReceiver>, IRedstoneReceiver> {
	@Override
	public IRedstoneReceiver apply(List<IRedstoneReceiver> iRedstoneReceivers) {
		return new WrappedReceiver(iRedstoneReceivers);
	}

	private class WrappedReceiver implements IRedstoneReceiver {
		private final Collection<IRedstoneReceiver> receiverSet;

		public WrappedReceiver(Collection<IRedstoneReceiver> receiverSet) {
			this.receiverSet = receiverSet;
		}

		@Override
		public void onRedstoneInputChange() {
			for (IRedstoneReceiver r : receiverSet) {
				r.onRedstoneInputChange();
			}
		}
	}
}
