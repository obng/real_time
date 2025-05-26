// src/RoleContext.js
import React, { createContext, useContext, useState } from 'react';
import PropTypes from 'prop-types';

const RoleContext = createContext();

export function RoleProvider({ children }) {
  const [role, setRole] = useState(null); // 'owner' 또는 'worker'
  return <RoleContext.Provider value={{ role, setRole }}>{children}</RoleContext.Provider>;
}

export function useRole() {
  return useContext(RoleContext);
}

RoleProvider.propTypes = {
  children: PropTypes.node.isRequired,
};