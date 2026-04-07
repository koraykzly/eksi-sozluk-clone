import Header from 'components/Headers/Header';
import UserSection from 'components/Headers/UserSection';

const AuthorizedLayout = () => {
  return (
    <>
      <Header userSection={UserSection}/>
    </>
  );
};

export default AuthorizedLayout;