// 主文件：App.js
import { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Outlet, useNavigate, Path } from "react-router-dom";
import { Layout, Menu, Breadcrumb } from "antd";
import { HomeOutlined, UserOutlined, ContactsOutlined } from "@ant-design/icons";
import Home from "./pages/Home";
import About from "./pages/About";
import Contact from "./pages/Contact";

const { Header, Content, Footer } = Layout;

// 带导航的布局组件
const LayoutWithNav = () => {
  const [current, setCurrent] = useState<string>("home");
  const navigate = useNavigate();

  // 处理菜单点击事件
  const handleMenuClick = (e: { key: string | ((prevState: string) => string) | Partial<Path>; }) => {
    setCurrent(e.key as string); // 更新选中状态
    navigate(e.key as string);   // 跳转到对应的路由
  };

  return (
    <Layout style={{ minHeight: "100vh" }}>
      {/* 顶部导航栏 */}
      <Header>
        <div className="logo" />
        <Menu
          theme="dark"
          mode="horizontal"
          selectedKeys={[current]}
          onClick={handleMenuClick}
          items={[
            { key: "/home", icon: <HomeOutlined />, label: "Home" },
            { key: "/about", icon: <UserOutlined />, label: "About" },
            { key: "/contact", icon: <ContactsOutlined />, label: "Contact" },
          ]}
        />
      </Header>

      {/* 内容区域 */}
      <Content style={{ padding: "0 50px" }}>
        <Breadcrumb style={{ margin: "16px 0" }}>
          <Breadcrumb.Item>Home</Breadcrumb.Item>
          <Breadcrumb.Item>{current.charAt(1).toUpperCase() + current.slice(2)}</Breadcrumb.Item>
        </Breadcrumb>
        <div className="site-layout-content" style={{ background: "#fff", padding: 24, minHeight: 280 }}>
          <Outlet />
        </div>
      </Content>

      {/* 底部 */}
      <Footer style={{ textAlign: "center" }}>Ant Design ©2023 Created by Ant UED</Footer>
    </Layout>
  );
};

// 主应用组件
const App = () => {
  return (
    <Router>
      <Routes>
        {/* 使用带导航的布局 */}
        <Route path="/" element={<LayoutWithNav />}>
          <Route index element={<Home />} /> {/* 默认子路由 */}
          <Route path="home" element={<Home />} />
          <Route path="about" element={<About />} />
          <Route path="contact" element={<Contact />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;