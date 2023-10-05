import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'localStorage',
  storage: localStorage,
});
export const ProfileImageState = atom({
  key: 'profileImage',
  default: '',
  effects_UNSTABLE: [persistAtom],
});

export const resetProfileImage = () => {
  localStorage.removeItem('recoil-persist-profileImage');
};
